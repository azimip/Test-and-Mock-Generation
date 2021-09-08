from collections import defaultdict


def intersection(lst1, lst2):
    return set(lst1).intersection(lst2)


if __name__ == '__main__':
    with open("msniffer-pdfbox_p-should_mocks-order5.txt") as msniffer_f:
        msniffer_array = []
        msniffer_pairs = defaultdict(list)
        for line in msniffer_f:
            clazz, dep, ord = line.split()
            msniffer_array.append(clazz)
            msniffer_pairs[clazz].append((dep, ord))

    with open("pankti-invoked_methods-classes.txt") as pankti_f:
        pankti_array = []
        pankti_class_method = defaultdict(list)
        for line in pankti_f:
            clazz, method = line.split()
            pankti_array.append(clazz)
            pankti_class_method[clazz].append(method)

    intersect = intersection(pankti_array, msniffer_array)

    with open("ord5_intersection", "w") as f:
        for i in intersect:
            f.write(f"{i} --> {pankti_class_method[i]} , {msniffer_pairs[i]} \n")

